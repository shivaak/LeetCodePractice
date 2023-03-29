import java.util.Stack;

public class SimplyPathLeetcode71 {

    public static void main(String[] args) {
        String path = "/a/./b/../../c/";

        String p[] = path.split("/");

        StringBuilder res = new StringBuilder();
        Stack<String> stack = new Stack<>();

        for(int i=0;i<=p.length-1;i++){
            if(p[i].trim().equals("") || p[i].trim().equals(".")) continue;
            if(p[i].equals("..")) {
                if(stack.size()>0) stack.pop();
                continue;
            }
            stack.push(p[i]);
        }

        if(stack.size()==0) res.insert(0, "/");

        while(stack.size() > 0){
            String curr =stack.pop();
            res.insert(0, "/"+curr);
        }

        System.out.println(res.toString());
    }
}
