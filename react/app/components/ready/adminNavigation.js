import React from 'react';

export default class AdminNavigation extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
        <div className="admin-navigation">
          <button>Добавить</button>
          <button>Изменить</button>
          <button>Расписание</button>
        </div>
    )
  }
}