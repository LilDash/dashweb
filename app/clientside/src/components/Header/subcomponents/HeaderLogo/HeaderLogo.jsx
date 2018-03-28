import * as React from 'react';
import './header-logo.scss';
import PropTypes from 'prop-types';


export class HeaderLogo extends React.PureComponent {
	render() {
		return (
			<a className='header-logo' href='/'>
				<span>D-Planet</span>
				<span>呆星</span>
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