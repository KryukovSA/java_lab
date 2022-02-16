package com.mycompany.app.client;

public class ClientMsg {
    int H, M, S;

    public ClientMsg(int H, int M, int S) {
        this.H = H;
        this.M = M;
        this.S = S;
    }

    public int getH() {
        return H;
    }

    public int getM() {
        return M;
    }

    public int getS() {
        return S;
    }

    public void setH(int H) {
        this.H = H;
    }

    public void setM(int M) {
        this.M = M;
    }

    public void setS(int S) {
        this.S = S;
    }
}
