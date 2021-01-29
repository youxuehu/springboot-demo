package com.example.springbootdemo.handler.job;

import org.eclipse.persistence.descriptors.ClassExtractor;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;

public class JobClassExtractor extends ClassExtractor {
    @Override
    public Class extractClassFromRow(Record record, Session session) {
        return null;
    }
}
