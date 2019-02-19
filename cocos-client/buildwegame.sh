C:/CocosCreator/CocosCreator.exe --path . --logfile web/log/build.log --build "configPath=build.json" \
&& rm -rf web/res \
&& mv build/wechatgame/res web/ \
&& ssh aliyun rm -rf /chuanjian-res/* \
&& scp -r web/res  aliyun:/chuanjian-res && chown -R nginx:nginx /chuanjian-res && chmod -R 740 /chuanjian-res