import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
public class FindNode {
    Sudoku content;
    FindNode father;
    FindNode child;
    String Operate;
    static Queue<FindNode> Open = new LinkedList<>();
    static Queue<FindNode> Close = new LinkedList<>();
    static HashSet<FindNode> hashSet = new HashSet<>();
    static Sudoku target = new Sudoku(
            new int[]{1, 2, 3, 8, 0, 4, 7 ,6, 5});
    public FindNode(Sudoku aContent) {
        content = new Sudoku(aContent.elements);
        father = null;
        child = null;
        Operate = "";
    }
    public FindNode(Sudoku aContent, FindNode aNode) {
        content = new Sudoku(aContent.elements);
        father = aNode;
        child = null;
        Operate = "";
    }
    public static FindNode Find() throws Exception {
        while (true) {
            if (Open.isEmpty()) {
                throw new Exception("寻找失败，无解");
            }
            FindNode test = Open.poll();
            Close.add(test);
            hashSet.add(test);
            if (test.content.equals(target)) {
                return test;
            } else {
                for (int i = 0; i < 4; i++) {
                    if (test.content.CanOperate(i) &&
                            !hashSet.contains(new FindNode(test.content.Operate(i)))) {
                        FindNode newNode = new FindNode(test.content.Operate(i), test);
                        if (i == 0) {
                            newNode.Operate += "Up";
                        }
                        if (i == 1) {
                            newNode.Operate += "Down";
                        }
                        if (i == 2) {
                            newNode.Operate += "Left";
                        }
                        if (i == 3) {
                            newNode.Operate += "Right";
                        }
                        Open.add(newNode);
                        hashSet.add(newNode);
                    }
                }
            }
        }
    }
    public static FindNode BackTrack(FindNode leaf) {
        while (leaf.father != null) {
            leaf.father.child = leaf;
            leaf = leaf.father;
        }
        return leaf;
    }

    @Override
    public String toString() {
        return "FindNode{" +
                "content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindNode findNode = (FindNode) o;
        return content.equals(findNode.content);
    }

    @Override
    public int hashCode() {
        int ans = 0;
        for (int i = 0; i < content.elements.length; i++) {
            ans += content.elements[i] * Math.pow(10, i);
        }
        return ans;
    }

    public static void main(String[] args) {
        FindNode RootNode = new FindNode(new Sudoku(new int[]{
                2, 6, 3,
                1, 0, 4,
                8, 7, 5
        }));
        Open.add(RootNode);
        //Find();
        //System.out.println(Find().content);
        try {
            FindNode ans = BackTrack(Find());
            while (ans.child != null) {
                System.out.println(ans.content);
                System.out.println(ans.child.Operate);
                ans = ans.child;
            }
            System.out.println(ans.content);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
