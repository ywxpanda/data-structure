package panda.scala.linkedlist

object RemoveLinkedListElement {
  def removeElements(head: ListNode, `val`: Int): ListNode = {
    var ahead = head
    while (ahead != null && ahead._x == `val`) {
      ahead = ahead.next
    }
    if (ahead == null) {
      return head
    }

    var prev = ahead

    while (prev.next != null) {
      if (prev.next._x == `val`) {
        prev.next = prev.next.next
      } else {
        prev = prev.next
      }
    }
    ahead
  }

  def removeElements2(head: ListNode, `val`: Int): ListNode = {
    if (head == null) {
      return null
    }
    head.next = removeElements2(head.next, `val`)
    //    if(head._x == `val`){
    //      return res
    //    }else{
    //      head.next = res
    //      return head
    //    }

    if (head._x == `val`) head.next else head
  }


}
