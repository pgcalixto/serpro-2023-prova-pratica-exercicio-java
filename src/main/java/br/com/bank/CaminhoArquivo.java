package br.com.bank;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {


    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        int idDiretorio = (id - 1) / 1000 + 1;

        String diretorio = "/tmp/" + idDiretorio;

        Path caminhoDiretorio = Paths.get(diretorio);
        Path caminhoArquivo = Paths.get(diretorio + "/" + id);

        return new CaminhoArquivo(caminhoDiretorio, caminhoArquivo);
    }

}
