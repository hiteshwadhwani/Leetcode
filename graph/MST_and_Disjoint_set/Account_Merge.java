package graph.MST_and_Disjoint_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Disjoint{
    int[] parent;
    int[] rank;
    Disjoint(int n){
        parent = new int[n];
        rank = new int[n];
        Arrays.fill(parent, -1);
    }
    public int find(int node){
        if(parent[node] == -1) return node;
        return parent[node] = find(parent[node]);
    }
    public void UnionByRank(int root1, int root2){
        int absoulute_parent_root1 = find(root1);
        int absolute_parent_root2 = find(root2);

        if(absoulute_parent_root1 == absolute_parent_root2) return;

        int rank1 = rank[absoulute_parent_root1];
        int rank2 = rank[absolute_parent_root2];

        if(rank1 > rank2){
            parent[absolute_parent_root2] = absoulute_parent_root1;
        }
        else if(rank1 < rank2){
            parent[absoulute_parent_root1] = absolute_parent_root2;
        }
        else{
            //rank1 == rank2
            parent[absolute_parent_root2] = absoulute_parent_root1;
            rank[absoulute_parent_root1]++;
        }
    }
}
public class Account_Merge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        Disjoint ds = new Disjoint(n);

        HashMap<String, Integer> hm = new HashMap<>();

        for(int i=0;i<n;i++){
            for(int j=1;j<accounts.get(i).size();j++){
                String email = accounts.get(i).get(j);

                if(!hm.containsKey(email)){
                    hm.put(email, i);
                }
                else{
                    ds.UnionByRank(hm.get(email), i);
                }
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for(int i=0;i<n;i++){
            ans.add(new ArrayList<>());
        }
        for(Map.Entry<String,Integer> entry : hm.entrySet()){
            int idx = ds.find(entry.getValue());
            ans.get(idx).add(entry.getKey());
        }

        List<List<String>> ret = new ArrayList<>();
        for(int i=0;i<ans.size();i++){
            if(ans.get(i).size() == 0) continue;
            Collections.sort(ans.get(i));
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for(int j=0;j<ans.get(i).size();j++){
                temp.add(ans.get(i).get(j));
            }
            ret.add(temp);
        }

        return ret;
        
    }
}
