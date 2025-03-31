# Android TV 行情显示
```
黄金行情 https://mt5.gslb.vip/mt/xauusd.html  
纳斯达克指数 https://mt5.gslb.vip/mt/index.html
原油 https://mt5.gslb.vip/mt/usoil.html 
美日行情 https://mt5.gslb.vip/mt/usdjpy.html
```

### 编译

构建所有变体：
```bash
./gradlew assembleDebug
```

构建特定变体：
```bash
# 构建黄金行情APK
./gradlew assembleGoldDebug

# 构建纳斯达克指数APK
./gradlew assembleNasdaqDebug

# 构建原油行情APK
./gradlew assembleOilDebug

# 构建美日行情APK
./gradlew assembleUsdjpyDebug
```

构建完成后，APK文件将位于以下目录：
- `app/build/outputs/apk/{variant}/debug/` 目录下
- 例如：美日行情的APK在 `app/build/outputs/apk/usdjpy/debug/` 目录