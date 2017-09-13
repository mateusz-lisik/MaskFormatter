package it.lisik.maskformatter;

import org.junit.Assert;
import org.junit.Test;

public class MaskFormatterTest {

    @Test
    public void testEmptyInput() {
        final Mask mask = MaskBuilder.build("###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', false);
        final MaskingResult result = formatter.process("");
        Assert.assertEquals("___", result.getText());
    }

    @Test
    public void testPartialInput() {
        final Mask mask = MaskBuilder.build("###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', false);
        final MaskingResult result = formatter.process("3");
        Assert.assertEquals("3__", result.getText());
        Assert.assertFalse(result.isValid());
    }

    @Test
    public void testOverflowInput() {
        final Mask mask = MaskBuilder.build("###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', false);
        final MaskingResult result = formatter.process("4321");
        Assert.assertEquals("432", result.getText());
        Assert.assertTrue(result.isValid());
    }

    @Test
    public void testPartialInputWithComplicatedMask() {
        final Mask mask = MaskBuilder.build("###/#####/##/#/###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', false);
        final MaskingResult result = formatter.process("321");
        Assert.assertEquals("321/_____/__/_/___", result.getText());
        Assert.assertFalse(result.isValid());
    }

    @Test
    public void testInputWithComplicatedMask() {
        final Mask mask = MaskBuilder.build("###/#####/##/#/###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', false);
        final MaskingResult result = formatter.process("123/12345/12/1/123");
        Assert.assertEquals("123/12345/12/1/123", result.getText());
        Assert.assertTrue(result.isValid());
    }

    @Test
    public void testAutoFollowLiterals() {
        final Mask mask = MaskBuilder.build("###/#####/##/#/###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', true);
        final MaskingResult result = formatter.process("12312345121123");
        Assert.assertEquals("123/12345/12/1/123", result.getText());
        Assert.assertTrue(result.isValid());
    }

    @Test
    public void testAutoFollowLiteralsWithDisabledAutoLiterals() {
        final Mask mask = MaskBuilder.build("###/#####/##/#/###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', false);
        final MaskingResult result = formatter.process("12312345121123");
        Assert.assertEquals("123/_____/__/_/___", result.getText());
        Assert.assertFalse(result.isValid());
    }

    @Test
    public void testAutoFollowLiteralsWithSomeLiterals() {
        final Mask mask = MaskBuilder.build("###/#####/##/#/###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', true);
        final MaskingResult result = formatter.process("12312345121/123");
        Assert.assertEquals("123/12345/12/1/123", result.getText());
        Assert.assertTrue(result.isValid());
    }

    @Test
    public void testAutoFollowLiteralsWithInvalidCharacterAfterLiteral() {
        final Mask mask = MaskBuilder.build("###/#####/##/#/###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', true);
        final MaskingResult result = formatter.process("123A");
        Assert.assertEquals("123/_____/__/_/___", result.getText());
        Assert.assertFalse(result.isValid());
    }

    @Test
    public void testAutoFollowLiteralsWithInvalidCharacterAfterLiteralFollowedByValidCharacters() {
        final Mask mask = MaskBuilder.build("###/#####/##/#/###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', true);
        final MaskingResult result = formatter.process("123A12345M12");
        Assert.assertEquals("123/_____/__/_/___", result.getText());
        Assert.assertFalse(result.isValid());
    }

    @Test
    public void testPlaceholderMask() {
        final Mask mask = MaskBuilder.build("###/#####/##/#/###");
        final MaskFormatter formatter = new MaskFormatter(mask, '_', false);
        Assert.assertEquals("___/_____/__/_/___", formatter.getPlaceholderMask());
    }

}