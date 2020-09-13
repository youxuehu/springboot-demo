package org.example.beans.utils;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionUtil {

    private DataSourceTransactionManager transactionManager;

    private TransactionStatus transactionStatus;

    public TransactionStatus begin() {
        transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        return transactionStatus;
    }

    public void commit() {
        transactionManager.commit(transactionStatus);
    }

    public void rollback() {
        transactionManager.rollback(transactionStatus);
    }

    public void setTransactionManager(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
