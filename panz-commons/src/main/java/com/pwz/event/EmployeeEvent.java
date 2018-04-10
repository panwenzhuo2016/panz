package com.pwz.event;

import java.util.EventObject;

public class EmployeeEvent extends EventObject {
    private int m_nReason;

    private int m_nType;

    private Object m_sSource;

    public EmployeeEvent(Object arg0, int reason, int type) {
        super(arg0);
        // TODO Auto-generated constructor stub
        this.setType(type);
        this.m_nReason = reason;
        this.m_sSource = arg0;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final int EVENT_WORK_ON = 0;

    public static final int EVENT_WORK_OFF = 1;

    public int getReason() {
        return m_nReason;
    }

    public int getType() {
        return m_nType;
    }

    public int setType(int nType) {
        return this.m_nType = nType;
    }

    @Override
    public Object getSource() {
        return (java.lang.Object) m_sSource;
    }
}