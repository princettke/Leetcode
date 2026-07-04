class Solution {

    class Pair {
        int node;
        int wt;

        Pair(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {

        ArrayList<Pair>[] graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            graph[u].add(new Pair(v, w));
            graph[v].add(new Pair(u, w));
        }

        boolean[] vis = new boolean[n + 1];

        dfs(1, graph, vis);

        return ans;
    }

    private void dfs(int src, ArrayList<Pair>[] graph, boolean[] vis) {

        vis[src] = true;

        for (Pair p : graph[src]) {

            ans = Math.min(ans, p.wt);

            if (!vis[p.node]) {
                dfs(p.node, graph, vis);
            }
        }
    }
}