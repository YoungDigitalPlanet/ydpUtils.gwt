package eu.ydp.gwtutil.client;

public class Alternative<M, S> {

    public static <M, O> Alternative<M, O> createForMain(M main) {
        Alternative<M, O> alt = new Alternative<M, O>();
        alt.main = main;
        return alt;
    }

    public static <M, O> Alternative<M, O> createForOther(O other) {
        Alternative<M, O> alt = new Alternative<M, O>();
        alt.other = other;
        return alt;
    }

    public static <M, O> Alternative<M, O> create(M main, O other) {
        Alternative<M, O> alt = new Alternative<M, O>();
        alt.main = main;
        alt.other = other;
        return alt;
    }

    private M main;
    private S other;

    private Alternative() {
    }

    public boolean hasMain() {
        return main != null;
    }

    public M getMain() {
        return main;
    }

    public S getOther() {
        return other;
    }

}
