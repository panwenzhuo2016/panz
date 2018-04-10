package com.pwz.event;

// 实现 EventListener接口
public interface EmployeeListener extends java.util.EventListener {

    // 使用监听器的时候，实现并添加到
    public void onEmPloyeeEvent(EmployeeEvent empEvent);

}


