function lacesarSignal() {
    class Pizza{
        constructor(yours){
            this.yours = yours;
            this.sequence = [1,2,3,4,5,6,7,8,9].map(n = >
        Math.pow(2,n))
            .sort(a,b) => a>b ?-1 :(a<b) ? 1 :0;
        this.cipher = [];
        }
        getCipher(){
            this.sequence.reduce((total,piece) =>{
                if (total + piece > this.yours) return total;
                this.cipher.push(piece);
                return total += piece;
            },0);
            this.cipher.sort()
        }
    }
}