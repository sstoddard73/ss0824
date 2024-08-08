import org.junit.*;

public class ToolTest {
    @Test
    public void Tool_DefaultConstructor() {
        Tool t = new Tool();
        Assert.assertEquals("", t.getToolCode());
        Assert.assertEquals("", t.getToolType());
        Assert.assertEquals("", t.getToolBrand());
    }

    @Test
    public void Tool_ConstructorWithInitialValues() {
        Tool t = new Tool("code", "type", "brand");
        Assert.assertEquals("code", t.getToolCode());
        Assert.assertEquals("type", t.getToolType());
        Assert.assertEquals("brand", t.getToolBrand());
    }

    @Test
    public void CheckoutRequest_CopyConstructor() {
        Tool t1 = new Tool("code", "type", "brand");
        Tool t2 = new Tool(t1);
        Assert.assertFalse(t1 == t2);
        Assert.assertEquals(t1.getToolCode(), t2.getToolCode());
        Assert.assertEquals(t1.getToolType(), t2.getToolType());
        Assert.assertEquals(t1.getToolBrand(), t2.getToolBrand());
    }

    @Test
    public void Tool_GettersAndSetters() {
        Tool t = new Tool();

        t.setToolCode("CODE");
        Assert.assertEquals("CODE", t.getToolCode());
    
        t.setToolType("TYPE");
        Assert.assertEquals("TYPE", t.getToolType());
    
        t.setToolBrand("BRAND");
        Assert.assertEquals("BRAND", t.getToolBrand());
    }
}
