package eu.ydp.gwtutil.client;

public final class PathUtil {

    private PathUtil() {
    }

    public static String resolvePath(String path, String base) {
        if (path.contains("://") || path.startsWith("/")) {
            return path;
        } else {
            if (base.endsWith("/") || base.endsWith("\\"))
                return base + path;
            else
                return base + "/" + path;
        }
    }

    public static String retrieveBasePath(String path) {
        int q = path.indexOf("?") - 1;
        if (q < 0)
            q = path.length();
        int s = path.lastIndexOf("/", q);
        int bs = path.lastIndexOf("\\", q);
        if (s > bs)
            return path.substring(0, s + 1);
        return path.substring(0, bs + 1);
    }

    public static String normalizePath(String path) { // NOPMD
        while (path.matches(".*\\\\[^\\\\]*\\\\[.]{2}.*")) {
            path = path.replaceAll("\\\\[^\\\\]*\\\\[.]{2}", "");
        }
        while (path.matches(".*/[^/]*/[.]{2}.*")) {
            path = path.replaceAll("/[^/]*/[.]{2}", "");
        }
        return path;
    }
}
