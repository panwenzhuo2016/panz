package com.pwz.event;

public class Message {
    public final static int TerminateType = -1;
    public int type;
    public Object data;
    MessageHandler handler;

    public Message(MessageHandler p, int t, Object d) {
        handler = p;
        type = t;
        data = d;
    }

    @Override
    public boolean equals(Object o) {
        Message e = (Message) o;
        return ((handler == e.handler) && (type == e.type) &&
                data.equals(e.data));
    }
}