<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="email code" name="description">
    <meta content="width=device-width, initial-scale=1" name="viewport">
</head>
<!--邮箱验证码模板-->
<body>
<div style="background-color:#ECECEC; padding: 35px;">
    <table align="center" cellpadding="0"
           style="
                   width: 600px;
                   height: 100%;
                   margin: 0px auto;
                   text-align: left;
                   position: relative;
                   border-top-left-radius: 5px;
                   border-top-right-radius: 5px;
                   border-bottom-right-radius: 5px;
                   border-bottom-left-radius: 5px;
                   font-size: 14px;
                   font-family:微软雅黑, 黑体;
                   line-height: 1.5;
                   box-shadow: rgb(153, 153, 153) 0px 0px 5px;
                   border-collapse: collapse;
                   background: #fff initial initial initial initial;">
        <tbody>
        <tr>
            <th style="
                    height: 25px;
                    line-height: 25px;
                    padding: 15px 35px;
                    border-bottom-width: 1px;
                    border-bottom-style: solid;
                    border-bottom-color: #0E3265;
                    background-color: #0E3265;
                    border-radius: 5px 5px 0px 0px;"
                valign="middle">
                <font face="微软雅黑" size="5" style="color: rgb(255, 255, 255); ">{1}</font>
            </th>
        </tr>
        <tr>
            <td style="word-break:break-all">
                <div style="
                         padding:25px 35px 40px;
                         background-color: white;
                         opacity:0.8; ">
                    <h2 style="margin: 5px 0px; ">
                        <font color="#333333" style="line-height: 20px; ">
                            <font size="4" style="line-height: 22px; ">
                                尊敬的用户：${title}</font>
                        </font>
                    </h2>
                    <!-- 中文 -->
                    <p>您好！
                        <br>
                        感谢您使用{1}，您的账号正在进行邮箱验证，验证码为：
                    <p style="text-align: center;"><span
                            style="
                                         color: #000e72;
                                         font-size: 24px;
                                         font-weight: bold">
                        {0}</span></p>
                    <br>此验证码有效期5分钟，请尽快填写验证码完成验证！</p>
                    <br>
                    <!-- 英文 -->
                    <h2 style="margin: 5px 0px; ">
                        <font color="#333333" style="line-height: 20px; ">
                            <font size="4" style="line-height: 22px; ">Dear user:</font>
                        </font>
                    </h2>
                    <p>Hello!
                        <br>Thanks for using {2}, your account is being authenticated by email, the
                        verification code is:
                    <p style="text-align: center;"><span
                            style="
                                         color: #000e72;
                                         font-size: 24px;
                                         font-weight: bold">
                        {0}</span></p>
                    <br>verification code valid for 5
                    minutes. Please fill in the
                    verification code as soon as
                    possible!</p>
                    <div style="width:100%;margin:0 auto;">
                        <div style="
                        padding:10px 10px 0;
                        border-top:1px solid #ccc;
                        color:#747474;
                        margin-bottom:20px;
                        line-height:1.3em;
                        font-size:12px;">
                            <p>{3}</p>
                            <p>此电子邮件仅限本人查看！如果有人要求你与他分享此
                                电子邮件或验证，或你认为误收此电子邮件，{4}</p>
                            <br>
                            <p>此为系统邮件，请勿回复<br>
                                Please do not reply to this system email
                            </p>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>