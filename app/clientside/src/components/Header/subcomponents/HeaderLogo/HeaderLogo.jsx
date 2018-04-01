import * as React from 'react';
import './header-logo.scss';
import PropTypes from 'prop-types';


export class HeaderLogo extends React.PureComponent {
	render() {
		return (
			<a className='header-logo' href='/' >
				<span className='header-logo-img' />
				<span className='header-logo-label-large'>{ '零号星球'}</span>
				<span className='header-logo-label-small'>{ '0-Planet'}</span>
			</a>
		);
  	}
}

// homeParams.propTypes = {
// 	imageSrc: PropTypes.string.isRequired,
// 	preloadColor: PropTypes.string.isRequired,
// };

// homeParams.defaultProps = {
// 	preloadColor: '#000000',
// };