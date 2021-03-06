package com.drcom.ListViewDemo;

import java.util.Hashtable;

public class MyLinkedList {

    /**
     * 链表中的节点，data代表节点的值，next是指向下一个节点的引用
     *
     *
     */
    public class Node {
        Node next = null;//下一个结点
        int data;//结点数据
        public Node(int data){
            this.data = data;
        }
    }

    /**链表的头结点*/
    Node head = null;

    /**
     * 向链表添加数据
     * 链表添加结点:
     * 找到链表的末尾结点，把新添加的数据作为末尾结点的后续结点
     * @param data  要添加的数据
     *  想法：    一开始也会想如果什么结点也没有。是不是需要判断插入的是第一个结点的问题，但写完后发现没有必要，
     *              是不是第一个结点操作都是一样的，所以通过移动的指针遍历整个链表，找到最后一个结点，往后添加即可。
     */
    public void addNode(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * 插入节点
     *
     * @param node  头指针 :插入的结点
     * @param index 要插入的位置 index:插入链表的位置，从1开始
     * @param value 要插入的值
     *              //前一个结点        当前位置        后一个结点
     *                 //temp            temp.next     temp.next.next
     *              先建立一个临时节点，从头节点开始，然后初始化一个要插入的节点，
     *              if判断是否到达指定位置。
     *              到达位置-(要插入的节点的 next指针  = 临时节点的 next 指针（当前位置 ）
     *                      临时节点的下一节点（当前位置 ） = 要插入的节点
     *              还没达到位置的话，临时节点=临时节点的下一节点
     */
    public  void insertNode(Node node, int index, int value) {

        //首先需要判断指定位置是否合法，
        if (index < 1 || index > length() + 1) {
            System.out.println("插入位置不合法。");
            return;
        }
        //临时节点，从头节点开始
        Node temp = head;

        //记录遍历的当前位置
        int currentPos = 0;

        //初始化要插入的节点
        Node insertNode = new Node(value);

//        while (temp.next != null) {
//
//            //找到上一个节点的位置了
//            if ((index - 1) == currentPos) {
//
//                //temp表示的是上一个节点
//
//                //将原本由上一个节点的指向交由插入的节点来指向
//                insertNode.next = temp.next;
//
//                //将上一个节点的指针域指向要插入的节点
//                temp.next = insertNode;
//
//                return;
//
//            }
//
//            currentPos++;
//            temp = temp.next;
//        }
        int length = 1;
        while(temp.next != null){//遍历单链表

            if(index == length){        //判断是否到达指定位置。
                //注意，我们的temp代表的是当前位置的前一个结点。
                //前一个结点        当前位置        后一个结点
                //temp            temp.next     temp.next.next
                //插入操作。
                insertNode.next = temp.next;
                temp.next = insertNode;
                return;
            }
            length++;
            temp = temp.next;
        }

    }


    /**
     * 链表删除结点:
     * 把要删除结点的前结点指向要删除结点的后结点，即直接跳过待删除结点
     * @param index
     * @return
     */
    public boolean deleteNode(int index){
        //首先需要判断指定位置是否合法，
        if(index<1 || index>length()){//待删除结点不存在
            System.out.println("删除位置不合法。");
            return false;
        }
        if(index == 1){//删除头结点
            head = head.next;
            return true;
        }
        Node preNode = head; //preNode表示的是上一个节点
        Node curNode = preNode.next;  //preNode.next表示的是想要删除的节点
        int i = 1;
        while(curNode != null){
            //找到上一个节点的位置了
            if(i==index){//寻找到待删除结点
                preNode.next = curNode.next;//待删除结点的前结点指向待删除结点的后结点
                return true;
            }
            //当先结点和前结点同时向后移
            preNode = preNode.next;//将想要删除的节点存储一下
            curNode = curNode.next;//想要删除节点的下一个节点交由上一个节点来控制
            i++;
        }
        return true;
    }

    //    /**
//     * 根据位置删除节点
//     *
//     * @param head  头指针
//     * @param index 要删除的位置
//     */
    public  void deleteNode(Node head, int index) {


        //首先需要判断指定位置是否合法，
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("删除位置不合法。");
            return;
        }

        //临时节点，从头节点开始
        Node temp = head;

        //记录遍历的当前位置
        int currentPos = 0;


        while (temp.next != null) {

            //找到上一个节点的位置了
            if ((index - 1) == currentPos) {

                //temp表示的是上一个节点

                //temp.next表示的是想要删除的节点

                //将想要删除的节点存储一下
                Node deleteNode = temp.next;

                //想要删除节点的下一个节点交由上一个节点来控制
                temp.next = deleteNode.next;


                //Java会回收它，设置不设置为null应该没多大意义了(个人觉得,如果不对请指出哦～)
                deleteNode = null;

                return;

            }
            currentPos++;
            temp = temp.next;
        }
    }

    /**
     * 求链表的长度
     * @return
     */
    public int length(){
        int length = 0;
        Node curNode = head;
        while(curNode != null){
            length++;
            curNode = curNode.next;
        }
        return length;
    }

    // TODO: 2018/12/4
    //    /**
//     * 获取链表的长度
//     * @param head 头指针
//     */
    public static int  linkListLength(Node head) {

        int length = 0;

        //临时节点，从首节点开始
        Node temp = head.next;

        // 找到尾节点
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        return length;
    }

    /**
     * 链表结点排序,并返回排序后的头结点:
     * 选择排序算法,即每次都选出未排序结点中最小的结点，与第一个未排序结点交换
     * @return
     */
    public Node linkSort(){
        Node curNode = head;
        while(curNode != null){
            Node nextNode = curNode.next;
            while(nextNode != null){
                if(curNode.data > nextNode.data){
                    int temp = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = temp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }

    /**
     * 对链表进行排序
     *
     * @param head
     *
     */
    public  void sortLinkList(Node head) {


        Node currentNode;

        Node nextNode;

        for (currentNode = head.next; currentNode.next != null; currentNode = currentNode.next) {

            for (nextNode = head.next; nextNode.next != null; nextNode = nextNode.next) {


                if (nextNode.data > nextNode.next.data) {

                    int temp = nextNode.data;
                    nextNode.data = nextNode.next.data;

                    nextNode.next.data = temp;

                }
            }


        }
    }

    /**
     * 遍历单链表，打印所有data
     * 打印结点
     */
    public void printLink(){
        Node temp = head; //临时节点，从首节点开始
        while(temp !=null){
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 去掉重复元素:
     * 需要额外的存储空间hashtable，调用hashtable.containsKey()来判断重复结点
     */
    public void distinctLink(){
        Node temp = head;
        Node pre = null;
        Hashtable<Integer, Integer> hb = new Hashtable<Integer, Integer>();
        while(temp != null){
            if(hb.containsKey(temp.data)){//如果hashtable中已存在该结点，则跳过该结点
                pre.next = temp.next;
            }else{//如果hashtable中不存在该结点，将结点存到hashtable中
                hb.put(temp.data, 1);
                pre=temp;
            }
            temp = temp.next;
        }
    }

    //
    /**
     * 删除链表重复数据(跟冒泡差不多，等于删除就是了)
     *
     * @param head 头节点
     */
    public static void deleteDuplecate(Node head) {

        //临时节点，(从首节点开始-->真正有数据的节点)
        Node temp = head.next;

        //当前节点(首节点)的下一个节点
        Node nextNode = temp.next;

        while (temp.next != null) {

            while (nextNode.next != null) {

                if (nextNode.next.data == nextNode.data) {

                    //将下一个节点删除(当前节点指向下下个节点)
                    nextNode.next = nextNode.next.next;

                } else {

                    //继续下一个
                    nextNode = nextNode.next;
                }
            }

            //下一轮比较
            temp = temp.next;
        }


    }


    /**
     * 找到链表中倒数第k个节点(设置两个指针first、second，让second比first快k个节点，同时向后遍历，当second为空，则p1为倒数第k个节点
     *
     * 返回倒数第k个结点,
     * 两个指针，第一个指针向前移动k-1次，之后两个指针共同前进，
     * 当前面的指针到达末尾时，后面的指针所在的位置就是倒数第k个位置
     * @param k
     * @return
     */
    public Node findReverNode(int k){
        if(k<1 || k>length()){//第k个结点不存在
            return null;
        }
        Node first = head;
        Node second = head;
        for(int i=0; i<k-1; i++){//前移k-1步
            first = first.next;
        }
        while(first.next != null){
            first = first.next;
            second = second.next;
        }
        return second;
    }

    /**
     * 查找正数第k个元素
     */
    public Node findNode(int k){
        if(k<1 || k>length()){//不合法的k
            return null;
        }
        Node temp = head;
        for(int i = 0; i<k-1; i++){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转链表，在反转指针钱一定要保存下个结点的指针
     */
    public void reserveLink(){
        Node curNode = head;//头结点
        Node preNode = null;//前一个结点
        while(curNode != null){
            Node nextNode = curNode.next;//保留下一个结点
            curNode.next = preNode;//指针反转
            preNode = curNode;//前结点后移
            curNode = nextNode;//当前结点后移
        }
        head = preNode;
    }


    /**
     * 通过递归从尾到头输出单链表
     * @param head 头节点
     *
     * 反向输出链表，三种方式：
     * 方法一、先反转链表，再输出链表，需要链表遍历两次
     * 方法二、把链表中的数字放入栈中再输出，需要维护额外的栈空间
     * 方法三、依据方法2中栈的思想，通过递归来实现，递归起始就是将先执行的数据压入栈中，再一次出栈
     */
    public void reservePrt(Node head){
        if(head != null){
            reservePrt(head.next);
            System.out.print(head.data+" ");
        }
    }

    /**
     * 寻找单链表的中间结点：
     * 方法一、先求出链表的长度，再遍历1/2链表长度，寻找出链表的中间结点
     * 方法二、：
     * 用两个指针遍历链表，一个快指针、一个慢指针，
     * 快指针每次向前移动2个结点，慢指针一次向前移动一个结点，
     * 当快指针移动到链表的末尾，慢指针所在的位置即为中间结点所在的位置
     */
    public Node findMiddleNode(){
        Node slowPoint = head;
        Node quickPoint = head;
        //quickPoint.next == null是链表结点个数为奇数时，快指针已经走到最后了
        //quickPoint.next.next == null是链表结点数为偶数时，快指针已经走到倒数第二个结点了
        //链表结点个数为奇数时,返回的是中间结点；链表结点个数为偶数时，返回的是中间两个结点中的前一个
        while(quickPoint.next != null && quickPoint.next.next != null){
            slowPoint = slowPoint.next;
            quickPoint = quickPoint.next.next;
        }
        return slowPoint;
    }

//    /**
//     * 查询单链表的中间节点
//     */
//
    public static Node searchMid(Node head) {

        Node p1 = head;
        Node p2 = head;


        // 一个走一步，一个走两步，直到为null，走一步的到达的就是中间节点
        while (p2 != null && p2.next != null && p2.next.next != null) {

            p1 = p1.next;
            p2 = p2.next.next;

        }

        return p1;


    }

    /**
     * 判断链表是否有环：
     * 设置快指针和慢指针，慢指针每次走一步，快指针每次走两步
     * 当快指针与慢指针相等时，就说明该链表有环
     */
    public boolean isRinged(){
        if(head == null){
            return false;
        }
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 返回链表的最后一个结点
     */
    public Node getLastNode(){
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 在不知道头结点的情况下删除指定结点：
     * 删除结点的重点在于找出其前结点，使其前结点的指针指向其后结点，即跳过待删除结点
     * 1、如果待删除的结点是尾结点，由于单链表不知道其前结点，没有办法删除
     * 2、如果删除的结点不是尾结点，则将其该结点的值与下一结点交换，然后该结点的指针指向下一结点的后续结点
     */
    public boolean deleteSpecialNode(Node n){
        if(n.next == null){
            return false;
        }else{
            //交换结点和其后续结点中的数据
            int temp = n.data;
            n.data = n.next.data;
            n.next.data = temp;
            //删除后续结点
            n.next = n.next.next;
            return true;
        }
    }

    /**
     * 判断两个链表是否相交：
     * 两个链表相交，则它们的尾结点一定相同，比较两个链表的尾结点是否相同即可
     */
    public boolean isCross(Node head1, Node head2){
        Node temp1 = head1;
        Node temp2 = head2;
        while(temp1.next != null){
            temp1 = temp1.next;
        }
        while(temp2.next != null){
            temp2 = temp2.next;
        }
        return temp1 == temp2;
    }

    /**
     * 如果链表相交，求链表相交的起始点：
     * 1、首先判断链表是否相交，如果两个链表不相交，则求相交起点没有意义
     * 2、求出两个链表长度之差：len=length1-length2
     * 3、让较长的链表先走len步
     * 4、然后两个链表同步向前移动，没移动一次就比较它们的结点是否相等，第一个相等的结点即为它们的第一个相交点
     */
    public Node findFirstCrossPoint(MyLinkedList linkedList1, MyLinkedList linkedList2){
        //链表不相交
        if(!isCross(linkedList1.head,linkedList2.head)){
            return null;
        }else{
            int length1 = linkedList1.length();//链表1的长度
            int length2 = linkedList2.length();//链表2的长度
            Node temp1 = linkedList1.head;//链表1的头结点
            Node temp2 = linkedList2.head;//链表2的头结点
            int len = length1 - length2;//链表1和链表2的长度差

            if(len > 0){//链表1比链表2长，链表1先前移len步
                for(int i=0; i<len; i++){
                    temp1 = temp1.next;
                }
            }else{//链表2比链表1长，链表2先前移len步
                for(int i=0; i<len; i++){
                    temp2 = temp2.next;
                }
            }
            //链表1和链表2同时前移,直到找到链表1和链表2相交的结点
            while(temp1 != temp2){
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            return temp1;
        }
    }

    /**
     * 对链表中的结点进行排序，按照从小到大的顺序，使用选择排序。
     *         使用双层遍历。第一层遍历，正常遍历链表，第二层遍历，遍历第一层遍历时所用的结点后面所有结点并与之比较
     *         选择排序比较简单，明白其原理，就能够写的出来。
     */
    public void selectSortNode(){
        //判断链表长度大于2，不然只有一个元素，就不用排序了。
        if(length()<2){
            System.out.println("无需排序");
            return;
        }
        //选择排序
        Node temp = head;            //第一层遍历使用的移动指针，最处指向头结点，第一个结点用temp.next表示
        while(temp.next != null){    //第一层遍历链表，从第一个结点开始遍历
            Node secondTemp = temp.next;        //第二层遍历使用的移动指针，secondTemp指向第一个结点，我们需要用到是第二个结点开始，所以用secondNode.next
            while(secondTemp.next != null){//第二层遍历,从第二个结点开始遍历
                if( temp.next.data > secondTemp.next.data){    //第二层中的所有结点依次与第一次遍历中选定的结点进行比较，
                    int t = secondTemp.next.data;
                    secondTemp.next.data =  temp.next.data;
                    temp.next.data = t;
                }
                secondTemp = secondTemp.next;
            }
            temp = temp.next;
        }
    }


    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
     *
     * @param head1 第一个有序链表
     * @param head2 第二个有序链表
     * @return 合并后的有序链表头
     */
    public  Node merge(Node head1, Node head2) {
        // 如果第一个链表为空，返回第二个链表头结点
        if (head1 == null) {
            return head2;
        }
        // 如果第二个结点为空，返回第一个链表头结点
        if (head2 == null) {
            return head1;
        }
        // 创建一个临时结点，用于添加元素时方便
//        Node root = new Node();
        // 用于指向合并后的新链的尾结点
//        Node pointer = root;
        Node pointer = null;
        // 当两个链表都不为空就进行合并操作
        while (head1 != null && head2 != null) {
            // 下面的操作合并较小的元素
            if (head1.data < head2.data) {
                 pointer = head1;
                head1 = head1.next;
            } else {
                 pointer = head2;
                head2 = head2.next;
            }
            // 将指针移动到合并后的链表的末尾
            pointer = pointer.next;
        }
        // 下面的两个if有且只一个if会内的内容会执行
        // 如果第一个链表的元素未处理完将其，接到合并链表的最后一个结点之后
        if (head1 != null) {
            pointer.next = head1;
        }
        // 如果第二个链表的元素未处理完将其，接到合并链表的最后一个结点之后
        if (head2 != null) {
            pointer.next = head2;
        }
        // 返回处理结果
        return pointer;
    }
    /**
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
     * 【使用的是递归的解法，不推荐，递归调用的时候会有方法入栈，需要更多的内存】
     *
     * @param head1 第一个有序链表
     * @param head2 第二个有序链表
     * @return 合并后的有序链表头
     */
    public  Node merge2(Node head1, Node head2) {
        // 如果第一个链表为空，返回第二个链表头结点
        if (head1 == null) {
            return head2;
        }
        // 如果第二个链表为空，返回第一个链表头结点
        if (head2 == null) {
            return head1;
        }
        // 记录两个链表中头部较小的结点
        Node tmp = head1;
        if (tmp.data < head2.data) {
            // 如果第一个链表的头结点小，就递归处理第一个链表的下一个结点和第二个链表的头结点
            tmp.next = merge2(head1.next, head2);
        } else {
            // 如果第二个链表的头结点小，就递归处理第一个链表的头结点和第二个链表的头结点的下一个结点
            tmp = head2;
            tmp.next = merge2(head1, head2.next);
        }
        // 返回处理结果
        return tmp;
    }
}
