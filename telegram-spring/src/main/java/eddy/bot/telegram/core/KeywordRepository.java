package eddy.bot.telegram.core;

import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import org.springframework.stereotype.Repository;

@Repository
public class KeywordRepository {

    private static RadixTree<String> keywords;

    public KeywordRepository(){
        keywords = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());
    }

    public RadixTree<String> getKeywords() {
        return keywords;
    }

}
