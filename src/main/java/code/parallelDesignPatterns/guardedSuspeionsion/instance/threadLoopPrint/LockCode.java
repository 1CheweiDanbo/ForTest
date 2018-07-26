package code.parallelDesignPatterns.guardedSuspeionsion.instance.threadLoopPrint;

/**
 * 锁码：公共数据区
 * 码值：码值为A，表示应该由A线程来执行，B,C线程等待
 *      码值为B,C同理。
 */
public class LockCode {

    private char code = 'A';
    private LockCode(){}
    public static LockCode newInstance(){
        return new LockCode();
    }

    /**
     * 循环设置锁码
     * 每一次调用，锁码按照A-B-C-A-...-的顺序循环往复
     */
    public void setCode(){
        this.code = (char) (this.code+1);
        if(this.code == 'D')
            this.code = 'A';
    }

    public char getCode() {
        return code;
    }
}
