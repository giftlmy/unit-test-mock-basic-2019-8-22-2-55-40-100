package cashregister;

public class MockPrinter extends Printer{
    private  String tempTxt = "";
    @Override
    public void print(String printThis) {
        super.print(printThis);
        this.tempTxt = printThis;
    }

    public String getTempTxt() {
        return tempTxt;
    }

    public void setTempTxt(String tempTxt) {
        this.tempTxt = tempTxt;
    }
}
