package com.sprixin.job.util;

public enum ScheduleStatusEnum {
        /**
         * 正常
         */
    	NORMAL(1),
        /**
         * 暂停
         */
    	PAUSE(0);

        private int value;

        ScheduleStatusEnum(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }