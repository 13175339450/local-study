TODO: 《一级缓存》 （同一个SqlSession）
    1. 什么时候不走缓存？
      1.1 SqlSession对象不是同一个，不走缓存。
      1.2 查询条件不一样，也不走缓存
    2. 什么时候一级缓存失效？
      第一次DQL和第二次DQL之间有如下任意的一种情况，都会让一级缓存情况
        2.1 执行了SqlSession的clearCache()方法，这是手动清空缓存。
        2.2 （当前SqlSession里）执行了INSERT、DELETE或UPDATE语句，不管是操作哪个表，都会清空一级缓存。（一般不太可能，因为一个SqlSession对应一个表）