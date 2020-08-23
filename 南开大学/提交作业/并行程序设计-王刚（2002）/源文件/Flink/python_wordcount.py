from imp import reload

from flink.plan.Environment import get_environment
from flink.functions.GroupReduceFunction import GroupReduceFunction
import sys
reload(sys)
sys.setdefaultencoding("utf-8")


class Adder(GroupReduceFunction):
    def reduce(self, iterator, collector):
        count, word = iterator.next()
        count += sum([x[0] for x in iterator])
        collector.collect((word, count))


if __name__ == "__main__":
    print("count=======%d", len(sys.argv))
    if len(sys.argv) != 3:
        print("Usage: wordcount <file>")
        sys.exit(-1)

    env = get_environment()
    data = env.read_text(sys.argv[1])
    data \
        .flat_map(lambda x, c: [(1, word) for word in x.lower().split()]) \
        .group_by(1) \
        .reduce_group(Adder(), combinable=True) \
        .write_text(sys.argv[2])
    env.execute(local=False)
