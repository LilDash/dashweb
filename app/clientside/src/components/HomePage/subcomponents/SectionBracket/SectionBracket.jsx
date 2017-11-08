import * as React from 'react';
import PropTypes from 'prop-types';
import './section-bracket.scss';


class SectionBracket extends React.PureComponent {

	constructor(props) {
	    super(props);
	  }

	render() {

		return (
			<div className={ ['section-bracket', this.props.direction].join(' ') }>
				<span>{ this.props.name }</span>
			</div>
		);
	}
};

SectionBracket.propTypes = {
	name: PropTypes.string,
	direction: PropTypes.oneOf(['up', 'down']).isRequired,
};

SectionBracket.defaultProps = {
	name: '',
	direction: 'down',
}


export { SectionBracket };