package com.catfish.sharding.utils;

/**
 * 雪花ID的生成的工具类
 * Created by qiuxiaotong on 2021/9/6
 */
public class SnowflakeIdWorker {

    /**
     * 构造函数
     *
     * @param modCoefficient 当前取余的系数，例如你设置为32，则生成的id % 32 则会等于你当前设置的modVal
     * @param modVal         当前生成的需要取余的值,假设你希望生成的雪花ID需要取余为8，则设置为8，这个值必须要小于31,且要小于modCoefficient的值
     * @param workerId       工作ID (0~31)
     * @param dataCenterId   数据中心ID (0~31)
     */
    public SnowflakeIdWorker(int modCoefficient, int modVal, long workerId, long dataCenterId) {
        this.modCoefficient = modCoefficient;
        this.modVal = modVal;
        // 开始时间戳设置为2019-12-31 23:59:59
        this.startTime = 1577807999000L;
        if (modVal > modCoefficient || modCoefficient < 0) {
            throw new IllegalArgumentException(String.format("modVal的值不能大于modCoefficient取余的系数的值，或者modCoefficient的值不能小于0", modCoefficient));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId不能大于" + maxWorkerId + "的值或者小于0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId不能大于" + maxDataCenterId + "的值或者小于0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 构造函数
     *
     * @param modCoefficient 当前取余的系数，例如你设置为32，则生成的id % 32 则会等于你当前设置的modVal
     * @param modVal         当前生成的需要取余的值,假设你希望生成的雪花ID需要取余为8，则设置为8，这个值必须要小于31,且要小于modCoefficient的值
     * @param startTime        起始的时间戳
     * @param workerId       工作ID (0~31)
     * @param dataCenterId   数据中心ID (0~31)
     */
    public SnowflakeIdWorker(int modCoefficient, int modVal, long startTime, long workerId, long dataCenterId) {
        this.modCoefficient = modCoefficient;
        this.modVal = modVal;
        this.startTime = startTime;
        if (modVal > modCoefficient || modCoefficient < 0) {
            throw new IllegalArgumentException(String.format("modVal的值不能大于modCoefficient取余的系数的值，或者modCoefficient的值不能小于0", modCoefficient));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId不能大于" + maxWorkerId + "的值或者小于0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId不能大于" + maxDataCenterId + "的值或者小于0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public SnowflakeIdWorker(long startTime, long workerId, long dataCenterId) {
        this.modCoefficient = 1;
        this.modVal = 0;
        this.startTime = startTime;
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId不能大于" + maxWorkerId + "的值或者小于0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId不能大于" + maxDataCenterId + "的值或者小于0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 当前取余的系数，例如你设置为32，则生成的id % 32 则会等于你当前设置的modVal
     */
    private final int modCoefficient;

    /**
     * 当前生成的需要取余的值,假设你希望生成的雪花ID需要取余为8，则设置为8，这个值必须要小于31,且要小于modCoefficient的值
     */
    private final int modVal;


    /**
     * 开始时间截
     */
    private final long startTime;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /**
     * 取余的值所占用的位数
     */
    private final long modBits = 5L;

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L - modBits;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = 12L;

    /**
     * 数据标识id向左移17位(7+5+5)
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits + modBits;

    /**
     * 时间截向左移22位(7+5+5+5)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits + modBits;

    /**
     * 生成序列的掩码，这里为128（2^7）
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;

    /**
     * 毫秒内序列(0~128)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;


    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("时钟异常，请重试生成雪花ID", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1 * modCoefficient) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - startTime) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence | modVal;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


    /**
     * 测试
     */
    public static void main(String[] args) {
        /**
         * 取余的系数设置为16，取余的值设置为13，这样你会发现生成的雪花ID的值被16取余以后为13
         */
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(16,13,1606660865000L, 0, 0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            System.out.println((id+"").length());
        }

        System.out.println("-------------------------");

        SnowflakeIdWorker idWorker2 = new SnowflakeIdWorker(16,13, 1L, 1l);
        System.out.println(idWorker2.nextId());
    }

}

