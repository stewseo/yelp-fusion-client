package io.github.stewseo.lowlevel.restclient;


import java.util.Iterator;

public interface NodeSelector {

    void select(Iterable<Node> nodes);
    /*
     * We were fairly careful with our choice of Iterable here. The caller has
     * a List but reordering the list is likely to break round-robin. Luckily
     * Iterable doesn't allow any reordering.
     */

    NodeSelector ANY = new NodeSelector() {
        @Override
        public void select(Iterable<Node> nodes) {
            // Intentionally does nothing
        }

        @Override
        public String toString() {
            return "ANY";
        }
    };

    NodeSelector SKIP_DEDICATED_MASTERS = new NodeSelector() {
        @Override
        public void select(Iterable<Node> nodes) {
            for (Iterator<Node> itr = nodes.iterator(); itr.hasNext();) {
                Node node = itr.next();
                if (node.getRoles() == null) continue;
                if (node.getRoles().isMasterEligible()
                        && !node.getRoles().canContainData()
                        && !node.getRoles().isIngest()) {
                    itr.remove();
                }
            }
        }

        @Override
        public String toString() {
            return "SKIP_DEDICATED_MASTERS";
        }
    };
}
