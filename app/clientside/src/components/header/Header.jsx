import * as React from 'react';
import './header.scss';
import { HeaderLogo } from './subcomponents/HeaderLogo/HeaderLogo';


export class Header extends React.Component {
	render() {
	//return <div className='header' style={{textAlign: 'center', padding:'30px'}}><label style={{fontSize:'50px', fontWeight:'600', margin: '20px', color:'red'}}>宝贝猪， 我爱你!!!!</label></div>;
		return (
			<div className='header'>
				<HeaderLogo />
			</div>
		);
	}
}