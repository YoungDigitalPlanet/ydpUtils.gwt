package eu.ydp.gwtutil.client.util;

public interface BrowserNativeInterface {
    /**
     * Sprawdza czy useragent pasuje do wzorca regex
     *
     * @param regex     regexpattern
     * @param userAgent useragent string
     * @return
     */
    public boolean isUserAgent(String regex, String userAgent);

    /**
     * Zwraca string useragent z przegladarki
     *
     * @return
     */
    public String getUserAgentStrting();

    /**
     * Czy aplikacja jest uruchomiona loklanie
     *
     * @return
     */
    public boolean isLocal();

    /**
     * Szerokosc urzadzenia
     */
    public int getScreenWidth();

    /**
     * Wysokosc urzadzenia
     */
    public int getScreenHeight();

    /**
     * Gestosc pikseli wyswietlacza
     */
    public double getPixelRatio();
}
