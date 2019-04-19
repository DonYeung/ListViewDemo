package com.drcom.ListViewDemo;

public class Test15 {



    public static  void main(String[] arg){

        MyLinkedList myLinkedList = new MyLinkedList();
        //添加链表结点
        myLinkedList.addNode(9);
        myLinkedList.addNode(8);
        myLinkedList.addNode(6);
        myLinkedList.addNode(3);
        myLinkedList.addNode(5);
        myLinkedList.addNode(6);

        //打印链表
        System.out.printf("打印链表-初始 = ");
        myLinkedList.printLink();
//        System.out.printf("length2:"+myLinkedList.linkListLength());

        // TODO: 2018/12/5  testd
//        myLinkedList.insertNode(myLinkedList.head,6,1);


        //测试链表结点个数
        System.out.println("链表结点个数为：" + myLinkedList.length());
        System.out.println("获取链表的长度test为：" + MyLinkedList.linkListLength(myLinkedList.head));

        //链表排序
        MyLinkedList.Node head = myLinkedList.linkSort();
        System.out.println("排序后的头结点为：" + head.data);
        System.out.printf("链表排序 = " );
        myLinkedList.printLink();

        // TODO: 2018/12/5 testd 链表排序
        myLinkedList.sortLinkList(myLinkedList.head);
        System.out.printf("对链表进行排序test = " );
        myLinkedList.printLink();

        //去除重复结点

        myLinkedList.distinctLink();
        System.out.printf("去除重复结点 = " );
        myLinkedList.printLink();

        // TODO: 2018/12/5 testd 删除链表重复数据(跟冒泡差不多，等于删除就是了) testd
//        myLinkedList.deleteDuplecate(myLinkedList.head);
//        System.out.printf("删除链表重复数据(跟冒泡差不多，等于删除就是了) testd = " );
//        myLinkedList.printLink();




        // TODO: 2018/12/5  testd 插入指定位置
        myLinkedList.insertNode(myLinkedList.head,2,1);
        System.out.printf("插入指定位置/遍历链表 = " );
        myLinkedList.printLink();

        //链表反转
        myLinkedList.reserveLink();
        System.out.printf("链表反转 = " );
        myLinkedList.printLink();

        //倒序输出/遍历链表
        System.out.printf("倒序输出/遍历链表 = " );
        myLinkedList.reservePrt(myLinkedList.head);
        System.out.println();

//        返回链表的中间结点
        MyLinkedList.Node middleNode = myLinkedList.findMiddleNode();
        System.out.println("中间结点的数值为："+middleNode.data);

        // TODO: 2018/12/6   中间结点的数值test 为 =
        MyLinkedList.Node middleNode2 = MyLinkedList.searchMid(myLinkedList.head);
        System.out.println("中间结点的数值test 为 = " + middleNode2.data);

        //判断链表是否有环
//        boolean isRinged = myLinkedList.isRinged();
//        System.out.println("链表是否有环：" + isRinged);
//        //将链表的最后一个结点指向头结点，制造有环的效果
//        MyLinkedList.Node lastNode = myLinkedList.getLastNode();
//        lastNode.next = myLinkedList.head;
//        isRinged = myLinkedList.isRinged();
//        System.out.println("链表是否有环：" + isRinged);

//        删除指定结点
        // TODO: 2018/12/6  testd 倒数第K个节点
        MyLinkedList.Node nk = myLinkedList.findReverNode(3);
        System.out.printf("指定结点 = " );
        System.out.println(nk.data);
        myLinkedList.deleteSpecialNode(nk);
        System.out.printf("删除指定结点 = " );
        myLinkedList.printLink();

        // TODO: 2018/12/6 testd deleteNode  根据位置删除节点
//        System.out.printf("删除前的链表： = "  );
//        myLinkedList.printLink();
//        myLinkedList.deleteNode(myLinkedList.head,3);
//        System.out.printf("删除后的链表： = "  );
//        myLinkedList.printLink();



//        //链表是否相交
//        //新链表
        MyLinkedList myLinkedList1 = new MyLinkedList();
        myLinkedList1.addNode(1);
        myLinkedList1.addNode(2);
        System.out.printf("新链表 myLinkedList1 = " );
        myLinkedList1.printLink();
        System.out.println("链表一和链表二是否相交"+myLinkedList.isCross(myLinkedList.head, myLinkedList1.head));
        //把第二个链表从第三个结点开始接在第二个链表的后面，制造相交的效果
        myLinkedList1.findNode(2).next = myLinkedList.findNode(3);
        System.out.printf("把第二个链表从第三个结点开始接在第一个链表的后面，制造相交的效果 = " );
        myLinkedList1.printLink();
        System.out.println("链表一和链表二是否相交 ="+myLinkedList.isCross(myLinkedList.head, myLinkedList1.head));


        //如果两个链表相交求链表相交的结点的值
        MyLinkedList myLinkedList2 = new MyLinkedList();
        myLinkedList2.addNode(1);
        myLinkedList2.addNode(2);
        myLinkedList2.findNode(2).next = myLinkedList.findNode(3);
        System.out.printf("新链表2 myLinkedList2 = " );
        myLinkedList2.printLink();
        MyLinkedList.Node n = myLinkedList2.findFirstCrossPoint(myLinkedList, myLinkedList2);
        if(n == null){
            System.out.println("链表不相交 = ");
        }else{
            System.out.println("两个链表相交，第一个交点的数值为：= " + n.data);
        }


        // TODO: 2018/12/6 testd
//        myLinkedList.selectSortNode();
//        System.out.printf("testd = " );
//        myLinkedList.printLink();


        myLinkedList.merge2(myLinkedList.head,myLinkedList1.head);
        myLinkedList.printLink();
    }




}


