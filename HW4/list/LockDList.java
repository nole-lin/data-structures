/* LockDList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class LockDList extends DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
    return new LockDListNode(item, prev, next);
  }

  /**
   *  LockDList() constructor for an empty LockDList.
   */
  public LockDList() {
    this.head = newNode(Integer.MIN_VALUE, this.head, this.head);
    this.head.next = this.head;
    this.head.prev = this.head;
    this.size = 0;
  }

  public LockDListNode front() {
    if (this.size != 0) {
      return (LockDListNode) head.next;
    } else {
      return null;
    }
  }

  public LockDListNode back() {
    if (this.size != 0) {
      return (LockDListNode) head.prev;
    } else {
      return null;
    }
  }

  public LockDListNode next(DListNode node) {
    if (node == null || node.next == this.head) {
      return null;
    } else {
      return (LockDListNode) node.next;
    }
  }

  public void remove(DListNode node) {
    if (node != null && this.size > 0) {
      LockDListNode lockNode = (LockDListNode) node;
      if (lockNode.locked == false) {
        if (this.size != 1) {
          DListNode tempNode = node;
          node.prev.next = tempNode.next;
          node.next.prev = tempNode.prev;
          this.size--;
        } else {
          this.head = newNode(Integer.MIN_VALUE, this.head, this.head);
          this.head.next = this.head;
          this.head.prev = this.head;
          this.size = 0;
        }
      }
    }
  }

  public void lockNode(DListNode node) {
    LockDListNode lockNode = (LockDListNode) node;
    lockNode.locked = true;
  }

  public static void main(String[] args) {
    /*
    DList l = new DList();
    System.out.println("### TESTING insertFront ###\nEmpty list is " + l.toString());

    l.insertBack(-1);
    l.insertFront(9);
    l.insertFront(5);
    l.insertBack(15);
    l.insertFront(-2);
    DListNode frontNode = l.front();
    DListNode nextNode = l.next(frontNode);
    nextNode = l.next(nextNode);
    l.remove(nextNode);
    System.out.println(l.toString());
    */

    LockDList l = new LockDList();
    System.out.println("### TESTING insertFront ###\nEmpty list is " + l.toString());

    l.insertBack(-1);
    l.insertFront(9);
    l.insertFront(5);
    l.insertBack(15);
    l.insertFront(-2);
    LockDListNode frontNode = l.front();
    LockDListNode nextNode = l.next(frontNode);
    nextNode = l.next(nextNode);
    l.remove(nextNode);
    l.lockNode(l.front());
    l.remove(l.front());
    System.out.println(l.toString()); 
  }
}