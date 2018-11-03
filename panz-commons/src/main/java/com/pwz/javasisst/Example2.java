package com.pwz.javasisst;

import org.apache.ibatis.javassist.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Example2 {
        //获取类的简单信息  
        public static void test01() throws Exception{ 
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("com.pwz.javasisst.Person");
             cc = pool.get(String.valueOf(Example2.class));
            //得到字节码  
            byte[] bytes = cc.toBytecode();
            System.out.println(bytes.length);
            for (byte aByte : bytes) {
                System.out.print((char) aByte+"");
            }


            System.out.println();
            System.out.println(Arrays.toString(bytes));


            System.out.println();

            for (int i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
                System.out.print((char) i+"");
            }
            System.out.println(Arrays.toString("我".getBytes()));

            System.out.println(cc.getName());//获取类名  
            System.out.println(cc.getSimpleName());//获取简要类名  
            System.out.println(cc.getSuperclass());//获取父类  
            System.out.println(cc.getInterfaces());//获取接口  
            System.out.println(cc.getMethods());//获取  
        }  
        //新生成一个方法  
        public static void test02() throws Exception{  
            ClassPool pool = ClassPool.getDefault();  
            CtClass cc = pool.get("com.pwz.javasisst.Person");
            //第一种  
            CtMethod cm = CtMethod.make("public String getName(){return name;}", cc);
            //第二种  
            //参数：返回值类型，方法名，参数，对象  
//            CtMethod cm = new CtMethod(CtClass.intType,"add",new CtClass[]{CtClass.intType,CtClass.intType},cc);
            cm.setModifiers(Modifier.PUBLIC);//访问范围  
            cm.setBody("{return $1+$2;}");  
            //cc.removeMethod(m) 删除一个方法  
            cc.addMethod(cm);  
            //通过反射调用方法  
            Class clazz = cc.toClass();  
            Object obj = clazz.newInstance();//通过调用无参构造器，生成新的对象  
            Method m = clazz.getDeclaredMethod("add", int.class,int.class);
            Object result = m.invoke(obj, 2,3);  
            System.out.println(result);  
        }  
          
        //修改已有的方法  
        public static void test03() throws Exception{  
            ClassPool pool  = ClassPool.getDefault();  
            CtClass cc = pool.get("com.pwz.javasisst.Person");
              
            CtMethod cm = cc.getDeclaredMethod("setA",new CtClass[]{pool.get("java.lang.String")});
            cm.insertBefore("System.out.println(\"调用前\");");//调用前  
            cm.insertAt(39, "System.out.println(\"39\");");//行号
            cm.insertAfter("System.out.println(\"调用后\");");//调用后  
              
            //通过反射调用方法  
            Class clazz = cc.toClass();  
            Object obj = clazz.newInstance();  
            Method m = clazz.getDeclaredMethod("setA", String.class);
            Object result = m.invoke(obj, "张三");  
            System.out.println(result);       
        }  
          
        //修改已有属性  
        public static void test04() throws Exception{  
            ClassPool pool  = ClassPool.getDefault();  
            CtClass cc = pool.get("com.pwz.javasisst.Person");
              
            //属性  
            CtField cf = new CtField(CtClass.intType,"age",cc);
            cf.setModifiers(Modifier.PRIVATE);  
            cc.addField(cf);  
            //增加响应的get set方法  
            cc.addMethod(CtNewMethod.getter("getAge",cf));
            cc.addMethod(CtNewMethod.setter("setAge",cf));  
              
            //访问属性  
            Class clazz = cc.toClass();  
            Object obj = clazz.newInstance();         
            Field field = clazz.getDeclaredField("age");
            System.out.println(field);  
            Method m = clazz.getDeclaredMethod("setAge", int.class);  
            m.invoke(obj, 16);  
            Method m2 = clazz.getDeclaredMethod("getAge", null);  
            Object resutl = m2.invoke(obj,null);          
            System.out.println(resutl);  
        }  
          
        //操作构造方法  
        public static void test05() throws Exception{  
            ClassPool pool = ClassPool.getDefault();  
            CtClass cc = pool.get("com.pwz.javasisst.Person");
              
            CtConstructor[] cons = cc.getConstructors();  
            for(CtConstructor con:cons){  
                System.out.println(con);  
            }  
        }  
        public static void main(String[] args) throws Exception {
            test01();
//            test02();
            test03();
            test04();
            test05();
    }
}