package com.yuheng.pangolin.constant;

public class RequestPathConstant {
    // user
    public static final String SIGN_UP = "/signUp";
    public static final String SIGN_IN = "/signIn";
    public static final String SIGN_OUT = "/signOut";
    // home
    public static final String ALL_LISTS = "/getAllList";
    public static final String HOME = "/home";
    // taskList
    public static final String TODAY_TASKS = "/getTasksInToday";
    public static final String IMPORTANT_TASKS = "/getTasksIsImportant";
    public static final String ALL_TASKS = "/getAllTasks";
    public static final String COMPLETED_TASKS = "/getTasksIsCompleted";
    public static final String ADD_TASK_LIST = "/addTaskList";
    public static final String ADD_TASK = "/addTask";
    public static final String EDIT_TASK = "/editTask";
    public static final String DELETE_TASK = "/deleteTask";
    public static final String SET_TASK_IMPORTANT = "/setTaskImportant";
    public static final String SET_TASK_COMPLETED = "/taskCompleted";
    // BBS
    public static final String ALL_BBSPOST = "/getAllBBSPost";
    public static final String CREATE_BBSPOST = "/createBBSPost";
    public static final String CREATE_BBSCOMMENT = "/createBBSComment";
}
