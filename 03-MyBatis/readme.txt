1.MyBatis规定:
  mapper.xml 文件里，namespace必须是被代理接口的全限定名，sql片段的id必须是对应的方法名。
  原因:
     在MyBatis底层进行动态代理为接口生成代理类时，使用了javassist:创建类池、创建类和创建接口，以及实现接口方法等等
     在实现接口方法时，需要拼接出对应的Code实现，其中就需要获取所需的SQL片段的内容，而存在如下疑问:
     1.如何定位到对应的mapper.xml？（可能存在多个） 2.如何定位到对应的SQL片段的id？
     此时MyBatis就规定了，namespace必须是被代理接口的全限定名，SQL片段的id必须是对应的方法名！
     利用约定大于配置，就能精准定位到对应的SQL片段了，从而实现了底层。

2.@Param注解的底层实现:
  当我们指定了@Param注解，MyBatis会将注解里的变量名按顺序放入Map<Integer, String> map里。
  其中key为从0开始的自然数，value为@Param注解对应的别名。同时会将形参里的参数值按顺序放入Object[]数组里
  此时进行匹配，需要让 paramName => paramValue
  而此时则遍历Map里的每一对Entry数组。以Entry数组的value作为paramName，Object[Entry.key()]作为paramValue。
  也可以理解为，
  for(int i = 0; i < map.size(); i++)
      map.get(i) => object[i]