import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.XSComponent;

import java.util.Collection;

/**
 * @author Kohsuke Kawaguchi
 */
public class SCDTest extends AbstractXSOMTest {
    /**
     * Taken from spec examples in section 4.2.16
     */
    public void testSpec() throws Exception {
        XSSchemaSet s = load("scdtest.xsd");

        MapNamespaceContext nsc = new MapNamespaceContext("", "", "my", "tns");

        assertOne("articleType complex type",s.select("type::my:articleType",nsc));
        assertOne("articleType complex type",s.select("/type::my:articleType",nsc));
        assertOne("section element declaration", s.select("/type::my:articleType/model::sequence/element::my:section",nsc));
        assertOne("appendix element declaration",s.select("/type::my:articleType/model::sequence/element::my:appendix",nsc));
        assertOne("anonymous complex type",s.select("/element::my:chapter/type::0",nsc));
        assertOne("wildcard",s.select("/element::my:chapter/type::0/model::sequence/any::*",nsc));
        assertOne("name attribute declaration",s.select("/element::my:chapter/type::0/attribute::name",nsc));
    }

    private void assertOne(String name, Collection<XSComponent> r) {
        assertEquals(1,r.size());
        assertEquals(name,r.iterator().next().toString());
    }
}