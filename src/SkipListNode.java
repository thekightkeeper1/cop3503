public class SkipListNode {

    SkipListNode next;
    SkipListNode below;
    final int value;

    public SkipListNode(int value) {
        this.value = value;
        this.next = null;
        this.below = null;
    }

    public SkipListNode(int value, SkipListNode next, SkipListNode below) {
        this.value = value;
        this.next = next;
        this.below = below;
    }

    public void setNext(SkipListNode next) {
        this.next = next;
    }

    public void setBelow(SkipListNode below) {
        this.below = below;
    }
}
