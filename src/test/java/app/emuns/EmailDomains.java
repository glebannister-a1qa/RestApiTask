package app.emuns;

public enum EmailDomains {

    ORG(".org"),
    COUK(".co.uk"),
    NET(".net"),
    GOV(".gov"),
    DE(".de"),
    FR(".fr"),
    NL(".nl"),
    COM(".com"),
    BE(".be"),
    JPG(".jpd");

    private String domain;

    EmailDomains(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

}
