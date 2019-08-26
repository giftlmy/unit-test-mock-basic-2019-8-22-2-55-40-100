package cashregister;


import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class CashRegisterTest {
    private static  MockPrinter mockPrinter;
    private static  CashRegister cashRegister;

    @BeforeAll
    public static void setup(){
        mockPrinter = new MockPrinter();
        cashRegister = new CashRegister(mockPrinter);
    }
    @BeforeEach
    public void clean(){
        mockPrinter.setTempTxt("");

    }

    @Test
    public void should_print_the_real_purchase_when_call_process() {
        //given
        Item[] items = { new Item("test product",1)};
        Purchase purchase = new Purchase(items);
//        MockPrinter mockPrinter = new MockPrinter();

        CashRegister cashRegister = new CashRegister(mockPrinter);
        //when
        cashRegister.process(purchase);
        //then
        Assertions.assertEquals("test product\t1.0\n",mockPrinter.getTempTxt());
    }
    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        MockPrinter   mockPrinter1 = new MockPrinter();
        CashRegister cashRegister1 = new CashRegister(mockPrinter1);
        //given
        StubPurchase st = new StubPurchase();
        //when
        cashRegister1 .process(st);
        //then
        Assertions.assertEquals("test product\t1.0\n",mockPrinter1.getTempTxt());
    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        //given
        Printer printer = Mockito.mock(Printer.class);
        Purchase purchase = Mockito.mock(Purchase.class);

        Mockito.when(purchase.asString()).thenReturn("test product");
        //when
        CashRegister cashRegister2 = new CashRegister(printer);
        cashRegister2.process(purchase);

        //then
        Mockito.verify(printer).print("test product");
    }

}
