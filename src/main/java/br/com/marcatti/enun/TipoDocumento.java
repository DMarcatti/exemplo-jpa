package br.com.marcatti.enun;

public enum TipoDocumento {

    CNPJ("cnpj"),
    CPF("cpf"),
    RG("rg"),
    TITULOELEITORAL("titulo eleitoral");

    private String tipoDocumento;

    TipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }


}
