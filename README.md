# サーブレット、JSPで作る議論アプリ　
## 開発環境
* Windows10
* Eclipse 2023-12
* Java11/Tomcat9
* MySQL 10.4.32
## 開発目的
* JavaによるWebアプリ開発の経験を就職に役立てるため。
* 意見交換ができる掲示板アプリを作りたかったため。
## 機能
* 議題の投稿
* 議題のピックアップ機能
* 議題に対する議論コメントとその返信機能
* コメント、返信に対するいいね機能
 ## 工夫したところ
 * いいねテーブルにユーザーIDとコメントIDを登録\
   いいねテーブルに登録されたコメントIDとコメントテーブルに登録されたコメントIDが同じかつ、\
   現在ログイン中のユーザーIDと同じユーザーIDの数をいいねボタンの表示といいねの登録と解除の条件にする\
   (条件となるユーザーIDの数にしているのは、値の取得をサブクエリで行っており、２つ以上のレコードを取得しないようにするため)\
   ユーザーIDがいいねテーブルになければ、いいねされていないのでボタンが押されたらいいねテーブルに登録するサーブレットが動くようにする\
   ユーザーIDがいいねテーブルにあれば、いいねされているので次にボタンが押されたらいいねテーブルから削除するサーブレットが動くようにする
 * コメントDAO内で返信テーブルのレコードを取得する処理も実行してコメント型のリスト内で返信型のリストを持てるようにし、コメントと返信を1画面で表示できるようにした
## 感想
* 難しく感じたところ
    * いいねをユーザーごとに管理すること
    * いいねの登録解除を同じボタンで実装すること
    * コメントと返信を1つの画面で表示すること
