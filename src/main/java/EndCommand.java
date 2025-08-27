public class EndCommand implements Command{
    private Hope hope;

    public EndCommand(Hope hope) {
        this.hope = hope;
    }
    @Override
    public void execute(Object o) {
        hope.shutdown();
    }
}
