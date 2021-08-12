package br.c.a.e.retrofit.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CallbackSemRetorno implements Callback<Void> {

    public static final String MENSAGEM_ERRO_RESPOSTA_NÃO_SUCEDIDA = "Resposta não sucedida";
    public static final String MENSAGEM_FALHA_DE_COMUNICAÇÃO = "Falha de comunicação: ";
    private final RespostaCallback callback;

    public CallbackSemRetorno(RespostaCallback callback) {
        this.callback = callback;
    }

    @Override
    @EverythingIsNonNull
    public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
            callback.quandoSucesso();
        } else {
            callback.quandoFalha(MENSAGEM_ERRO_RESPOSTA_NÃO_SUCEDIDA);
        }
    }

    @Override
    @EverythingIsNonNull
    public void onFailure(Call<Void> call, Throwable t) {
        callback.quandoFalha(MENSAGEM_FALHA_DE_COMUNICAÇÃO + t.getMessage());
    }

    public interface RespostaCallback {
        void quandoSucesso();
        void quandoFalha(String erro);
    }
}
