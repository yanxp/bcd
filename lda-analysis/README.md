topic 0 :
公司=0.009538408630174017
市场=0.008848009751698062
中国=0.008756489189917975
企业=0.0068280510303913395
发展=0.005991900977658479
目前=0.004408401842957633
产品=0.0041981128106208625
服务=0.003756081561227181
已经=0.003410105744626914
记者=0.003289155629929911

topic 1 :
专业=0.00872496522205349
工作=0.008108171408190876
学生=0.00793944661866665
学校=0.006307480899983371
考生=0.005295205701518912
大学=0.0052671267600129445
教育=0.0051547106121291805
考试=0.00507254577329609
人才=0.004037747449851247
招聘=0.003913811857165103

topic 2 :
医院=0.006197066939127888
治疗=0.0048149451145789455
患者=0.0032264139617756145
健康=0.0026521203697810374
手术=0.0025525793863978826
女性=0.0023724111474892357
专家=0.0021711200905248276
发现=0.0021645199996586885
病人=0.0021567877663232846
医生=0.002155356316589454


你们先了解一下lda文本分类算法的原理 在看一下我的代码 基本我已经写好了
lad算法输出类似上述，在方案二实现中，我们需要把训练出的主题词汇存入数据库待查询时调出。
数据库 表名 citys
字段 cityid(int) cityname(text) vocalbulary(text)
前段任务：
  需要搭建一个前段架构（参考我做的jar窗口） 
                       一个列表用于选择不同的城市
                       一个textArea用显示主题词汇 显示特效自行发挥
                       一个文件选择textfield用于选择文件给后台训练
   界面尽量简洁美观 表单提交任务（列表中选中的城市）到后台

后台任务：
    部署代码到BCD服务器 需要跟那边的人沟通商量
         1、表单提交任务（前端选择列表中的城市）到后台后 后台返回相应城市的主题词汇
         另外我会把一个百度云账号给你们，里面有100个城市的微博csv数据，需要将其转换为txt文本再去训练，其中不能有符号。
         之后按照每个城市的微博数据在服务器后台先训练完成 并将其保存在数据库中 每个城市一行数据，按上面说数据库的字段 之后待前端调用
         2、若前端提交一个微博文件则后台需要将其训练好保存在数据库中，待前端查询。
ps:前端任务比较轻松，所以两人可以合作完成。
    ok，大概就这样，有问题问我，加油，谢谢！