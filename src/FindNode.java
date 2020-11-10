import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
import java.math.*;
public class FindNode {
    Sudoku content;
    FindNode father;
    static Queue<FindNode> Open = new LinkedList<>();
    static Queue<FindNode> Close = new LinkedList<>();
    static HashSet<FindNode> hashSet = new HashSet<>();
    static Sudoku target = new Sudoku(
            new int[]{1, 2, 3, 8, 0, 4, 7 ,6, 5});
    public FindNode(Sudoku aContent) {
        content = new Sudoku(aContent.elements);
        father = null;
    }
    public FindNode(Sudoku aContent, FindNode aNode) {
        content = new Sudoku(aContent.elements);
        father = aNode;
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
                        Open.add(newNode);
                        hashSet.add(newNode);
                    }
                }
            }
        }

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
                1, 2, 3, 8, 0, 4, 7 ,5, 6}));
        Open.add(RootNode);
        //Find();
        //System.out.println(Find().content);
        try {
            System.out.println(Find().content);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
