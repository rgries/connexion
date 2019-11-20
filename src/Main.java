import structure.*;

public class Main {
    public static void main(String [] args){
        testingUnionFind();


    }

    private static void testingUnionFind() {
        AscendingNode root1 = new AscendingNode(1);

        AscendingNode child1 = new AscendingNode(2, root1);
        AscendingNode child2 = new AscendingNode(3, root1);
        AscendingNode child3 = new AscendingNode(4, child1);
        AscendingNode child4 = new AscendingNode(5, child3);

        AscendingNode root2 = new AscendingNode(6);
        AscendingNode child5 = new AscendingNode(7, root2);

        AscendingNode[] a = new AscendingNode[7];

        UnionFind unionFind = new UnionFind(a);

        a[0] = root1.getParent();
        a[1] = child1.getParent();
        a[2] = child2.getParent();
        a[3] = child3.getParent();
        a[4] = child4.getParent();
        a[5] = root2.getParent();
        a[6] = child5.getParent();

        System.out.println("n : parent");
        for(int i = 0; i< a.length; i++){
            if(a[i] != null) System.out.println(i+1 +" : " + a[i].getValue());
        }
        System.out.println("********");

        unionFind.union(a[3],a[6]);
        for(int i = 0; i< a.length; i++){
            if(a[i] != null) System.out.println(i+1 +" : " + a[i].getValue());
        }
    }
}
