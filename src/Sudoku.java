import java.util.Arrays;

public class Sudoku {
    int[] elements;
    int SpaceCurr;
    public Sudoku(int[] aElements) {
        elements = aElements.clone();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == 0) {
                SpaceCurr = i;
                break;
            }
        }
    }
    public boolean Up() {
        if (SpaceCurr < 3) {
            return false;
        }
        else {
            elements[SpaceCurr] = elements[SpaceCurr - 3];
            SpaceCurr -= 3;
            elements[SpaceCurr] = 0;
        }
        return true;
    }
    public boolean Down() {
        if (SpaceCurr > 5) {
            return false;
        }
        else {
            elements[SpaceCurr] = elements[SpaceCurr + 3];
            SpaceCurr += 3;
            elements[SpaceCurr] = 0;
        }
        return true;
    }
    public boolean Left() {
        if (SpaceCurr == 0 || SpaceCurr == 3 || SpaceCurr == 6) {
            return false;
        }
        else {
            elements[SpaceCurr] = elements[SpaceCurr - 1];
            SpaceCurr--;
            elements[SpaceCurr] = 0;
        }
        return true;
    }
    public boolean Right() {
        if (SpaceCurr == 2 || SpaceCurr == 5 || SpaceCurr == 8) {
            return false;
        }
        else {
            elements[SpaceCurr] = elements[SpaceCurr + 1];
            SpaceCurr++;
            elements[SpaceCurr] = 0;
        }
        return true;
    }
    public boolean CanOperate(int OperateCode) {
        if (OperateCode == 0) {
            if (SpaceCurr < 3) {
                return false;
            }
        }
        if (OperateCode == 1) {
            if (SpaceCurr > 5) {
                return false;
            }
        }
        if (OperateCode == 2) {
            if (SpaceCurr == 0 || SpaceCurr == 3 || SpaceCurr == 6) {
                return false;
            }
        }
        if (OperateCode == 3) {
            if (SpaceCurr == 2 || SpaceCurr == 5 || SpaceCurr == 8) {
                return false;
            }
        }
        return true;
    }
    public Sudoku Operate(int OperateCode) {
        Sudoku ans = new Sudoku(this.elements);
        if (OperateCode == 0) {
            ans.elements[ans.SpaceCurr] = ans.elements[ans.SpaceCurr - 3];
            ans.SpaceCurr -= 3;
            ans.elements[ans.SpaceCurr] = 0;
        }
        if (OperateCode == 1) {
            ans.elements[ans.SpaceCurr] = ans.elements[ans.SpaceCurr + 3];
            ans.SpaceCurr += 3;
            ans.elements[ans.SpaceCurr] = 0;
        }
        if (OperateCode == 2) {
            ans.elements[ans.SpaceCurr] = ans.elements[ans.SpaceCurr - 1];
            ans.SpaceCurr--;
            ans.elements[ans.SpaceCurr] = 0;
        }
        if (OperateCode == 3) {
            ans.elements[ans.SpaceCurr] = ans.elements[ans.SpaceCurr + 1];
            ans.SpaceCurr++;
            ans.elements[ans.SpaceCurr] = 0;
        }
        return ans;
    }
    public boolean equals(Sudoku sudoku) {
        return Arrays.equals(this.elements, sudoku.elements);
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = 0; i < 3; i++) {
            ans += Integer.toString(elements[i]);
            ans += " ";
        }
        ans += "\n";
        for (int i = 3; i < 6; i++) {
            ans += Integer.toString(elements[i]);
            ans += " ";
        }
        ans += "\n";
        for (int i = 6; i < 9; i++) {
            ans += Integer.toString(elements[i]);
            ans += " ";
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 0};
        Sudoku sudoku = new Sudoku(array);
        System.out.println(sudoku);
        sudoku.Up();
        System.out.println(sudoku);
    }
}
