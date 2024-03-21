import { HeartTwoTone, SmileTwoTone } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-components';
import { useIntl } from '@umijs/max';
import { Alert, Card, Typography } from 'antd';
import React from 'react';
import {PageHeader} from '@ant-design/pro-components'

const Admin: React.FC = ({ children }: React.PropsWithChildren<{}>) => {
  const intl = useIntl();
  return (
      <PageContainer
          content={intl.formatMessage({
            id: 'pages.admin.subPage.title',
            defaultMessage: 'This page can only be viewed by admin',
          })}
      >
        {children}
      </PageContainer>
  );
};

export default Admin;
