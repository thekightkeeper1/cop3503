import java.util.ArrayList;
import java.util.Collections;

public class SkipList {

    SkipListNode head;
    int maxLevel = 0;

    public SkipList() {
        this.head = null;
    }

    // Inserts, duh
    public int insert(int value) {
        SkipListNode newNode = new SkipListNode(value);
        ArrayList<SkipListNode> breadCrumbs = search(value, true);
        Collections.reverse(breadCrumbs);

        // Our base cases where we just insert normally
        if (breadCrumbs.isEmpty()) {
            this.head = newNode;
            return 1;
        } else if (breadCrumbs.size() == 1) {
            if (value <= this.head.value) {
                newNode.next = this.head;
                this.head = newNode;
                return 1;
            }
            this.head.next = newNode;
            return 1;
        }
        // Now comes the complex inserting algorithm
        // It will amount to e do a normal insert at level 0;
        //

        int numPromotions = 0;
        while (Math.random() >= .5) numPromotions++;
        int level = 0;
        int i = 0;
        SkipListNode cur = breadCrumbs.getFirst();
        SkipListNode prevInsertion = null;  // new nodes always have their below set to the new node one level down


        // This should stop when we inserted on every level til and including promotion
        while (level <= numPromotions) {
            newNode = new SkipListNode(value);  // Every level needs a distinct new node

            // This promotes the head
            if (cur == this.head) {
                this.head = new SkipListNode(this.head.value, null, this.head);
                this.maxLevel++;
                cur = this.head;    // So maxLevel <= level
            }

            // This is a normal linked-list insertion.
            newNode.next = cur.next;
            newNode.below = prevInsertion;
            cur.next = newNode;
            prevInsertion = newNode;

            // Next we find the next level to update.
            // We need another loop to do this, since we ignore nodes in the same level
            if (level == this.maxLevel /*|| level == numPromotions*/) {
                cur = this.head;
            } else {
                // This `break`s when we find a new cur on a higher level
                while (/*i < breadCrumbs.size()-1*/ true) { // I think the level checker above will protect this
                    SkipListNode parent = breadCrumbs.get(i + 1);
                    if (parent.below != null && parent.below == cur) {  // Jumped a level
                        i++;
                        cur = parent;
                        break;
                    }
                    cur = parent;  // todo major bug i didnt have this originally
                    i++;
                }
            }
            level++;

        }
        return 0;
    }
    public int insert(int values[]) {
        for (int value : values) {
            insert(value);
        }
        return 1;
    }

    public String toString() {
        return levelToString(this.head);
    };

    private String levelToString(SkipListNode head) {
        if (head == null) return "";
        String levelString = "";
        SkipListNode n = head;
        while (n != null) {
            levelString += String.format(" %3d", n.value);
            n = n.next;
        }
        levelString += "\n" + levelToString(head.below);
        return levelString;
    }

    public int delete() {
        return 0;
    }

    // Returns empty list if not found, unless it was forced to return.
    public ArrayList<SkipListNode> search(int value, boolean forceBreadcrumbs) {
        ArrayList<SkipListNode> breadcrumbs = new ArrayList<>();
        ArrayList<SkipListNode> emptyBreadcrumbs = new ArrayList<>();
        if (isEmpty()) return breadcrumbs;
        SkipListNode cur = this.head;
        breadcrumbs.add(cur);
        while (true) {

            // We only move to a node if it is not null, so this is always valid
            if (cur.value == value) {
                breadcrumbs.add(cur);
                if (cur.below != null) cur = cur.below;
                return breadcrumbs;
            }

            // Didn't find it, so we have to move on either to next or below.
            // First we check if we hit an edge, in which case we move down or just stop
            // Bottom right corner
            if (cur.next == null && cur.below == null) return forceBreadcrumbs ? breadcrumbs : emptyBreadcrumbs;

            // edge of this level
            if (cur.next == null) {
                cur = cur.below;
            } else if (cur.next.value > value) { // Next is too high
                cur = cur.below;
            } else { // Next is less than or equal to our target
                cur = cur.next;
            }
            breadcrumbs.add(cur);

        }
    }

    public ArrayList<SkipListNode> search(int value) {
        return search(value, false);
    }

    public boolean isEmpty() {
        return this.head == null;
    }
}


