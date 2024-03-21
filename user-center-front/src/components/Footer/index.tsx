import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';
import {GITHUB_LINK} from "@/constant";

const Footer: React.FC = () => {
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      links={[
        {
          key: 'Github',
          title: 'Github',
          href: GITHUB_LINK,
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/chenyufish/user_center',
          blankTarget: true,
        },
        {
          key: 'Leetcode',
          title: 'Leetcode',
          href: 'https://leetcode.cn/u/fishjun/',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
