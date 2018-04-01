import * as React from 'react';
import './footer.scss';

export class Footer extends React.Component {
  render() {
    return (
    	<div className='footer'>
    		<div className='footer-inner'>
					<div className='col-l'>
						<p className='coming-soon'>{'即将推出, 敬请期待：'}</p>
						<div className='links'>
							<a>{'零星旅淘'}</a>
							<a>{'零星家园'}</a>
							<a>{'零星小站'}</a>
							<a>{'零星自由行'}</a>
							<a>{'零星花语'}</a>
						</div>
						<p className='footer-icp'><a href='http://www.miitbeian.gov.cn' >粤ICP备17015068号-1</a></p>
						<p className='footer-copyright'>COPYRIGHT 2018 0PLANET. ALL RIGHTS RESERVED.</p>
					</div>
					<div className='col-r'>
						<div className='footer-contact'>
							<span>{'添加微信号联系我们：'}</span>
							<img src='/assets/images/wechat_qr.jpg' title={'添加微信号'} />
						</div> 
					</div>
    		</div>  		
    	</div>
    );
  }
}